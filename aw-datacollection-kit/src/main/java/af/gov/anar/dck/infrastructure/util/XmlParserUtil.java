package af.gov.anar.dck.infrastructure.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.model.InstanceHistory;
import af.gov.anar.dck.instance.service.InstanceHistoryService;
import af.gov.anar.dck.instance.service.InstanceWatcherService;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;

@Component
public class XmlParserUtil {
    @Autowired
    UserService userService;
    @Autowired
	InstanceHistoryService instanceHistoryService;
	@Autowired
	InstanceWatcherService instanceWatcherService;

	ObjectMapper mapper = new ObjectMapper();
	XPath xPath = XPathFactory.newInstance().newXPath();

	public boolean isValid(String xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = null;
        try
        {
            builder = factory.newDocumentBuilder();
            builder.parse(new InputSource(new StringReader(xmlString)));
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return false;
	}


	// delete image from edit instance form it
	public Instance deleteImageTextNode(Instance instance, String imageName, String fieldName, boolean isRepeatField){

		XPath xPath = XPathFactory.newInstance().newXPath();
		String xmlContent = instance.getXmlContent();

		if (isValid(xmlContent)) {
			Document instanceXmlDoc = parse(xmlContent);
			Element rootNode = instanceXmlDoc.getDocumentElement();

		try {
			// if isRepeatField=false then it means that it is not repeat field
			// the field exists only value have to set for that node
			if(!isRepeatField) {

				NodeList nodeList = (NodeList) xPath.compile(fieldName).evaluate(rootNode, XPathConstants.NODESET);
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node nNode = nodeList.item(i);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						
						if (eElement.getTextContent().equals(imageName))
						((Element) nNode).setTextContent("");
				 	}
				}
			} else {

				NodeList nodeList = (NodeList) xPath.compile(fieldName).evaluate(rootNode, XPathConstants.NODESET);

				for (int i = 0; i < nodeList.getLength(); i++) {
					Node nNode = nodeList.item(i);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						
						if (eElement.getTextContent().equals(imageName))
								((Node) ((Element) nNode).getParentNode().getChildNodes()).removeChild(eElement);
				 	}
				}
			}

			xmlContent = convertToString(instanceXmlDoc);
			System.out.println(xmlContent);
			instance.setXmlContent(xmlContent);
			return instance;
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return null;
	}

	public boolean isValidThrowException(String xmlString) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = null;
        builder = factory.newDocumentBuilder();
        builder.parse(new InputSource(new StringReader(xmlString)));
        return true;
	}

	public Document parse(String xmlString) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try
        {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return null;
	}

	public String convertToString(Document doc) {
		String xmlString = "";
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);

			xmlString = writer.toString();
			// System.out.println(xmlString);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return xmlString;
	}

	public String createXMLDocAsString(String rootNodeName, Map<String, Object> properties)
			throws ParserConfigurationException {
		Document xmlDoc = createXMLDoc(rootNodeName,properties);
		return convertToString(xmlDoc);
	}
	public Document createXMLDoc(String rootNodeName, Map<String, Object> properties)
			throws ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
		Document newDoc = builder.newDocument();
		
		// root element
		Element newDocRootEl = newDoc.createElement(rootNodeName);
		newDoc.appendChild(newDocRootEl);

		Attr attrEv = newDoc.createAttribute("xmlns:ev");
		attrEv.setValue("http://www.w3.org/2001/xml-events");
		newDocRootEl.setAttributeNode(attrEv);

		Attr attrOrx = newDoc.createAttribute("xmlns:orx");
		attrOrx.setValue("http://openrosa.org/xforms");
		newDocRootEl.setAttributeNode(attrOrx);

		Attr attrOdk = newDoc.createAttribute("xmlns:odk");
		attrOdk.setValue("http://www.opendatakit.org/xforms");
		newDocRootEl.setAttributeNode(attrOdk);

		Attr attrH = newDoc.createAttribute("xmlns:h");
		attrH.setValue("http://www.w3.org/1999/xhtml");
		newDocRootEl.setAttributeNode(attrH);

		Attr attrJr = newDoc.createAttribute("xmlns:jr");
		attrJr.setValue("http://openrosa.org/javarosa");
		newDocRootEl.setAttributeNode(attrJr);

		Attr attrXsd = newDoc.createAttribute("xmlns:xsd");
		attrXsd.setValue("http://www.w3.org/2001/XMLSchema");
		newDocRootEl.setAttributeNode(attrXsd);
			
		// create properties
		Set<String> keysList = properties.keySet();
		for (String key : keysList) {
			Object value = properties.get(key);
			String propertyNodeName = key;
			if(key.contains("/")) {
				String[] keyParts = key.split("/");
				propertyNodeName = keyParts[keyParts.length - 1];
			}

			Element property = newDoc.createElement(propertyNodeName);
			property.setTextContent(value.toString());
			newDocRootEl.appendChild(property);
		}
		return newDoc;
	}

	public Document getXFormInstanceNodeTree(Document xmlDoc) {
		String tagName = "instance";
		NodeList nList = xmlDoc.getElementsByTagName(tagName);
		Node instanceNodeChild = nList.item(0).getFirstChild();

		Element targetEl = null;

		while (instanceNodeChild != null) {
			if (instanceNodeChild.getNodeType() == Node.ELEMENT_NODE) {
				targetEl = (Element) instanceNodeChild;
				break;
			}
			instanceNodeChild = instanceNodeChild.getNextSibling();
		}

		System.out.println("Tagrget NodeName: " + targetEl.getNodeName());

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
        DocumentBuilder builder = null;
        try
        {
            builder = factory.newDocumentBuilder();
            Document newDoc = builder.newDocument();
            Node importedNode = newDoc.importNode(targetEl, true);

            newDoc.appendChild(importedNode);
            Element newDocRootEl = newDoc.getDocumentElement();

            Attr attrEv = newDoc.createAttribute("xmlns:ev");
            attrEv.setValue("http://www.w3.org/2001/xml-events");
    		newDocRootEl.setAttributeNode(attrEv);

    		Attr attrOrx = newDoc.createAttribute("xmlns:orx");
    		attrOrx.setValue("http://openrosa.org/xforms");
    		newDocRootEl.setAttributeNode(attrOrx);

    		Attr attrOdk = newDoc.createAttribute("xmlns:odk");
    		attrOdk.setValue("http://www.opendatakit.org/xforms");
    		newDocRootEl.setAttributeNode(attrOdk);

    		Attr attrH = newDoc.createAttribute("xmlns:h");
    		attrH.setValue("http://www.w3.org/1999/xhtml");
    		newDocRootEl.setAttributeNode(attrH);

    		Attr attrJr = newDoc.createAttribute("xmlns:jr");
    		attrJr.setValue("http://openrosa.org/javarosa");
    		newDocRootEl.setAttributeNode(attrJr);

    		Attr attrXsd = newDoc.createAttribute("xmlns:xsd");
    		attrXsd.setValue("http://www.w3.org/2001/XMLSchema");
    		newDocRootEl.setAttributeNode(attrXsd);

            System.out.println("New Doc Root NodeName: " + newDocRootEl.getNodeName());

            return newDoc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getXFormInstanceId(Form form) {
		String xmlContent = form.getXmlContent();
		Document xformDoc = parse(xmlContent);
		return getXFormInstanceId(xformDoc);
	}

	// it fetch the ID attribute form <instance> tag of XForm
	public String getXFormInstanceId(Document xformDoc) {
		String IdAttr = null;
		if(xformDoc != null) {
			NodeList instanceNodeList = xformDoc.getElementsByTagName("instance");
			NodeList instanceNodeChildren = instanceNodeList.item(0).getChildNodes();
			for (int i = 0; i < instanceNodeChildren.getLength(); i++) {
				Node node = instanceNodeChildren.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					IdAttr = ((Element) node).getAttribute("id");
				}
			}
		}
		return IdAttr;
	}

	// it fetch the ID attribute from instanceXML of instance
	public String getInstanceXMLId(Document xmlDoc) {
		Element rootNode = xmlDoc.getDocumentElement();
		System.out.println("Root Element Id: " + rootNode.getAttribute("id"));
		return rootNode.getAttribute("id");
	}

	public String getXFormEnvSlug(Document xmlDoc) {
		Element rootNode = xmlDoc.getDocumentElement();
		System.out.println("Root Element Id: " + rootNode.getAttribute("id"));
		String envSlug = rootNode.getAttribute("envSlug");
		System.out.println("EnvSlug: " + envSlug);
		if(envSlug == "") {
			return null;
		}
		return envSlug;
	}

	// add envSlug attribute to first child node of <instance>
	public Form addEnvSlugToFormXML(Form form) {
		String xmlContent = form.getXmlContent();
		String envSlug = form.getEnvSlug();
		Document xformDoc = parse(xmlContent);
		if(xformDoc != null) {
			NodeList instanceNodeList = xformDoc.getElementsByTagName("instance");
			NodeList instanceNodeChildren = instanceNodeList.item(0).getChildNodes();
			for (int i = 0; i < instanceNodeChildren.getLength(); i++) {
				Node node = instanceNodeChildren.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					((Element) node).setAttribute("envSlug", envSlug);
				}
			}

			xmlContent = convertToString(xformDoc);
			form.setXmlContent(xmlContent);
		}
		return form;
	}

	// add envSlug attribute to first child node of <instance>
	public Instance addInstanceHeadersToXMLContent(Instance instance) {
		String xmlContent = instance.getXmlContent();
		if (isValid(xmlContent)) {
			Document xmlDoc = parse(xmlContent);
			if(xmlDoc != null) {
				Element root = xmlDoc.getDocumentElement();

				Element headersEl = xmlDoc.createElement("headers");


				// Instance Title
				Element title = xmlDoc.createElement("title");
				title.appendChild(xmlDoc.createTextNode(instance.getTitle()==null? "": instance.getTitle()));
				headersEl.appendChild(title);
				// Instance Owner
				Element ownerEl = xmlDoc.createElement("owner");
				User owner = instance.getOwner();
				String userName = "";
				String userEmail = "";
				if(owner != null) {
					userName = owner.getName();
					userEmail = owner.getEmail();
				}
				Element ownerNameEl = xmlDoc.createElement("owner_name");
				ownerNameEl.appendChild(xmlDoc.createTextNode(userName));
				Element ownerEmailEl = xmlDoc.createElement("owner_email");
				ownerEmailEl.appendChild(xmlDoc.createTextNode(userEmail));
				ownerEl.appendChild(ownerNameEl);
				ownerEl.appendChild(ownerEmailEl);
				headersEl.appendChild(ownerEl);
				// Instance Created At
				Element createdAt = xmlDoc.createElement("created_at");
				createdAt.appendChild(xmlDoc.createTextNode(instance.getCreatedAt()==null? "": instance.getCreatedAt().toString()));
				headersEl.appendChild(createdAt);
				// Instance Updated At
				Element updatedAt = xmlDoc.createElement("updated_at");
				updatedAt.appendChild(xmlDoc.createTextNode(instance.getUpdatedAt()==null? "": instance.getUpdatedAt().toString()));
				headersEl.appendChild(updatedAt);
				// Instance Current Step
				Element currentStep = xmlDoc.createElement("current_step");
				currentStep.appendChild(xmlDoc.createTextNode(instance.getCurrentStep()==null? "": instance.getCurrentStep()));
				headersEl.appendChild(currentStep);

				root.appendChild(headersEl);
				xmlContent = convertToString(xmlDoc);
				instance.setXmlContent(xmlContent);
			}
		}
		return instance;
	}

	public String updateInstanceXmlbyKeyValueMapAsString(Instance instance, Form form, Map<String, Object> keyValueMap , Long instanceId, User loggedInUser, boolean isFirstUpdate, boolean createElWhenMatchNotFound, boolean notifyWatchers) {
		Document instanceXMLDoc = updateInstanceXmlbyKeyValueMap(instance, form, keyValueMap , instanceId, loggedInUser, isFirstUpdate, createElWhenMatchNotFound, notifyWatchers);
		String instanceXmlContent = convertToString(instanceXMLDoc);
		return instanceXmlContent;
	}

	public Document updateInstanceXmlbyKeyValueMap(Instance instance, Form form, Map<String, Object> keyValueMap , Long instanceId, User loggedInUser, boolean isFirstUpdate, boolean createElWhenMatchNotFound, boolean notifyWatchers) {
		JSONObject jsonObj = new JSONObject(keyValueMap);
		String instanceXmlContent = instance.getXmlContent();
		String formXmlContent = form.getXmlContent();
		Document instanceXmlObj = parse(instanceXmlContent);
		Document formXmlObj = parse(formXmlContent);

		if(instanceXmlObj != null) {
			return updateInstanceXmlbyJson(instanceXmlObj, formXmlObj, jsonObj , instanceId, loggedInUser, isFirstUpdate, createElWhenMatchNotFound, notifyWatchers);
		}
		return null;
	}

	public Document updateInstanceXmlbyJson(Document instanceXmlObj, Document formXmlObj, JSONObject jsonObj , Long instanceId, User loggedInUser, boolean isFirstUpdate, boolean createElWhenMatchNotFound, boolean notifyWatchers) {
        XPath xPath = XPathFactory.newInstance().newXPath();
		JsonParserUtil jsonParserUtil = new JsonParserUtil();
		try {
			List<InstanceHistory> instanceHistores = new ArrayList<>();
			Iterator<String> jsonObjectKeys = jsonObj.keys();
			while(jsonObjectKeys.hasNext()) {
				boolean keyMatchFound = false;
			    String jsonKey = jsonObjectKeys.next();
//			    String jsonValue = jsonParserUtil.parseJsonObjToXFormValue(jsonObj.get(jsonKey));

			    Object jsonValue = jsonObj.get(jsonKey);

			    System.out.println(jsonKey + " : " + jsonValue);

				NodeList nodeList = (NodeList) xPath.compile(jsonKey).evaluate(instanceXmlObj, XPathConstants.NODESET);

				JSONArray jsonArrayVal = null;
				Node repeatElement = null;
				Node formInstanceDummyElement = null;
				// check if value is Array or not
				if(jsonValue instanceof JSONArray) {
					//find node with nodeset=jsonkey
					NodeList formNodeList = (NodeList) xPath.compile("//repeat[@nodeset=\"" + jsonKey + "\"]").evaluate(formXmlObj, XPathConstants.NODESET);

					for (int i = 0; i < formNodeList.getLength(); i++) {
						Node node = formNodeList.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							repeatElement = node;
							keyMatchFound = true;
						}
					}

//					System.out.println(repeatElement);

					if(repeatElement != null) {
						jsonArrayVal = jsonObj.getJSONArray(jsonKey);

						// this will fetch the repeat node which is located in instance node of form.
						NodeList formInstanceDummyNodeList = (NodeList) xPath.compile("/" + jsonKey).evaluate(formXmlObj, XPathConstants.NODESET);

						for (int i = 0; i < formInstanceDummyNodeList.getLength(); i++) {
							Node node = formInstanceDummyNodeList.item(i);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								formInstanceDummyElement = node;
							}
						}

//						System.out.println("formInstanceDummyElement");
//						System.out.println(formInstanceDummyElement);
					}

//					System.out.println("JsonArrayVal: ");
//					System.out.println(jsonArrayVal);
				}

				Node rootNode = null;
				if (repeatElement != null) {
					// remove all the existing loops and create new entries based on data we
					// received from clientside
					for (int i = 0; i < nodeList.getLength(); i++) {
						Node node = nodeList.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							if (rootNode == null) {
								rootNode = node.getParentNode();
							}
							rootNode.removeChild(node);
						}
					}

					//loop through array of objects that is a loop we received from client-side
					for (int i = 0 ; i < jsonArrayVal.length(); i++) {
						JSONObject arrayObjEl = jsonArrayVal.getJSONObject(i);
//						System.out.println("arrayObjEl: ");
//						System.out.println(arrayObjEl);

						Element loopNode = instanceXmlObj.createElement(formInstanceDummyElement.getNodeName());
						rootNode.appendChild(loopNode);

						Node dummyRepeatChildNode = formInstanceDummyElement.getFirstChild();
						while (dummyRepeatChildNode != null) {
							if (dummyRepeatChildNode.getNodeType() == Node.ELEMENT_NODE) {
								// System.out.println(dummyRepeatChildNode.getNodeName());
								String nodeName = dummyRepeatChildNode.getNodeName();

								String updatedValue = "";

								for(String key : arrayObjEl.keySet()) {
									if(key.endsWith(nodeName)) {
										updatedValue = arrayObjEl.getString(key);
										break;
									}
								}

								Element node = instanceXmlObj.createElement(nodeName);
								node.appendChild(instanceXmlObj.createTextNode(updatedValue));
								loopNode.appendChild(node);

							}
							dummyRepeatChildNode = dummyRepeatChildNode.getNextSibling();
						}

					}
				} else {
					// this condition will handle the non-repeat questions and update them
					Node xmlElement = null;
					System.out.println("nodeList length: " + nodeList.getLength());
					for (int i = 0; i < nodeList.getLength(); i++) {
						Node node = nodeList.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							System.out.println(
									"Node Name = " + node.getNodeName() + "; Value = " + node.getTextContent());
							xmlElement = nodeList.item(i);
							System.out.println("xmlElement: " + xmlElement.toString());
						}
					}

					if(xmlElement != null) {
						keyMatchFound = true;

						String xmlElementValue = getNodeValue(xmlElement);
						System.out.println("xmlElementName: " + xmlElement.getNodeName() + ", xmlElementValue: " + xmlElementValue);

						String finalUpdatedValue = jsonParserUtil.parseJsonObjToXFormValue(jsonValue);

						if(xmlElementValue != null && !xmlElementValue.equals(finalUpdatedValue)) {
                            if(!isFirstUpdate){
                            InstanceHistory instanceHistory = new InstanceHistory();
                            Long userId = loggedInUser.getId();
                            instanceHistory.setUserId(userId);
                            instanceHistory.setInstanceId(instanceId);
                            instanceHistory.setField(xmlElement.getNodeName());
                            instanceHistory.setNewValue(finalUpdatedValue);
                            instanceHistory.setOldValue(xmlElementValue);
							instanceHistoryService.create(instanceHistory);
							instanceHistores.add(instanceHistory);
                            }
							System.out.println("Values do not match");

							xmlElement = setNodeValue(xmlElement, finalUpdatedValue);
						}
					}
				}

				// the key does not match with xml instance
				if(!keyMatchFound && createElWhenMatchNotFound) {
					System.out.println("Creating new element: " + jsonKey);
				}
			}
			if(notifyWatchers) {
				instanceWatcherService.sendEmail(instanceId, instanceHistores);
			}
			
			return instanceXmlObj;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//if TEXT_NODE is passed then value is set.
	// if ELEMENT_NODE is passed then it is considered to not have another child except text
	public Node setNodeValue(Node node, String value) {
		if (node.getNodeType() == Node.TEXT_NODE) {
			node.setNodeValue(value);
		} else if (node.getNodeType() == Node.ELEMENT_NODE) {
			node.setTextContent(value);
		}
		return node;
	}

	//if TEXT_NODE is passed then value is returned.
	// if ELEMENT_NODE is passed then it is considered to not have another child except text
	public String getNodeValue(Node node) {
		if (node != null) {
			if (node.getNodeType() == Node.TEXT_NODE) {
				return node.getNodeValue();
			} else if (node.getNodeType() == Node.ELEMENT_NODE) {
				return node.getTextContent();
			}
		}
		return null;
	}

	// instance xml column parsing
	public Instance setInstancePropertiesByXmlContent(Instance instance) {
		String instanceXmlContent = instance.getXmlContent();
		if (isValid(instanceXmlContent)) {
			Document instanceXmlDoc = parse(instanceXmlContent);
			Element rootNode = instanceXmlDoc.getDocumentElement();

			Map<String, Object> properties = new LinkedHashMap<>();
			//get all child nodes of instance xml
			NodeList nodeList = rootNode.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					properties.put("/" + rootNode.getNodeName() + "/" + node.getNodeName(), getNodeValue(node));
				}
			}
			instance.setProperties(properties);
		}

		return instance;
	}

	public Instance addImageToInstanceXmlContent(Instance instance, String fieldName, File savedImageFile, boolean isRepeatField) {
		XPath xPath = XPathFactory.newInstance().newXPath();
		String xmlContent = instance.getXmlContent();
		Element childNode = null;

		if (isValid(xmlContent)) {
			Document instanceXmlDoc = parse(xmlContent);
			Element rootNode = instanceXmlDoc.getDocumentElement();

			try {
				// if isRepeatField=false then it means that it is not repeat field
				// the field exists only value have to set for that node
				if(!isRepeatField) {
					NodeList nodeList = (NodeList) xPath.compile(fieldName).evaluate(rootNode, XPathConstants.NODESET);

					for (int i = 0; i < nodeList.getLength(); i++) {
						Node node = nodeList.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							((Element) node).setTextContent(savedImageFile.getName());
						}
					}
				} else {
					String [] xpathParts = fieldName.split("/");

					// the index 0 is empty and index 1 is the root node

					for(int i = 2; i < xpathParts.length; i++) {
						Element newNode = instanceXmlDoc.createElement(xpathParts[i]);
						if(childNode == null) {
							rootNode.appendChild(newNode);
						}
						if(childNode != null) {
							childNode.appendChild(newNode);
						}
						childNode = newNode;
					}

					if(childNode != null) {
						childNode.appendChild(instanceXmlDoc.createTextNode(savedImageFile.getName()));
					}
				}

				xmlContent = convertToString(instanceXmlDoc);
				System.out.println(xmlContent);
				instance.setXmlContent(xmlContent);

				return instance;
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}


	public static String xmlToJson(String xmlContent)
    {
        //converting xml to json
        JSONObject obj = XML.toJSONObject(xmlContent);
        System.out.println(obj.toString());
        return obj.toString();
	}

	public Form setAndFetchXFormInstancePropertiesWithMetatdata(Form form) {

		String xmlContent = form.getXmlContent();
		// This flag will check if the field is of type geometry
		Boolean hasGeometry = false;
		List<String> odkGeoTypes = new ArrayList<String>();
		odkGeoTypes.add("geotrace");
		odkGeoTypes.add("geoshape");
		odkGeoTypes.add("geopoint");

		if(isValid(xmlContent)) {
			String tagName = "h:body";
			Document formXmlDoc = parse(xmlContent);
			Element rootNode = formXmlDoc.getDocumentElement();

			NodeList h_bodyList = formXmlDoc.getElementsByTagName(tagName);
			// h_bodyNode: will select the <h:body> node of xForm
			Node h_bodyNode = h_bodyList.item(0);

			ArrayNode propertiesList = mapper.createArrayNode();

			NodeList h_bodyChildren = h_bodyNode.getChildNodes();
			for (int i = 0; i < h_bodyChildren.getLength(); i++) {
				Node h_bodyChild = h_bodyChildren.item(i);

				if (h_bodyChild.getNodeType() == Node.ELEMENT_NODE) {
					String nodeName = h_bodyChild.getNodeName();
					if(nodeName == "group") {
						NodeList groupChildren = h_bodyChild.getChildNodes();
						for (int gIndex = 0; gIndex < groupChildren.getLength(); gIndex++) {
							Node groupChild = groupChildren.item(gIndex);
							if (groupChild.getNodeType() == Node.ELEMENT_NODE) {
								String groupChildNodeName = groupChild.getNodeName();

								if(groupChildNodeName == "label") {

								} else if(groupChildNodeName == "repeat") {
									NodeList repeatChildren = groupChild.getChildNodes();
									for (int rIndex = 0; rIndex < repeatChildren.getLength(); rIndex++) {
										Node repeatChild = repeatChildren.item(rIndex);
										if (repeatChild.getNodeType() == Node.ELEMENT_NODE) {
											ObjectNode property = getProperty(rootNode, repeatChild);
											if(hasGeometry != true && odkGeoTypes.contains(property.get("type").asText())) {
												hasGeometry = true;
											}
											propertiesList.add(property);
										}
									}
								} else {
									ObjectNode property = getProperty(rootNode, groupChild);
									if(hasGeometry != true && odkGeoTypes.contains(property.get("type").asText())) {
										hasGeometry = true;
									}
									propertiesList.add(property);
								}

							}
						}
					} else {
						ObjectNode property = getProperty(rootNode, h_bodyChild);
						if(hasGeometry != true && odkGeoTypes.contains(property.get("type").asText())) {
							hasGeometry = true;
						}
						propertiesList.add(property);
					}


				}
			}

			form.setHasGeometry(hasGeometry);
			form.setProperties(propertiesList.toString());
			return form;
		}
		return null;
	}

	private ObjectNode getProperty(Element rootNode, Node targetNode) {
		ObjectNode property = mapper.createObjectNode();
		String href = ((Element) targetNode).getAttribute("ref");
		property.put("name", href);

		try {
			NodeList bindNodeList = (NodeList) xPath.compile("//bind[@nodeset=\"" + href + "\"]").evaluate(rootNode, XPathConstants.NODESET);

			Node relevantBindNode = null;
			for (int j = 0; j < bindNodeList.getLength(); j++) {
				Node bindNode = bindNodeList.item(j);
				if (bindNode.getNodeType() == Node.ELEMENT_NODE) {
					relevantBindNode = bindNode;
				}
			}

			if(relevantBindNode != null) {
				NamedNodeMap attributes = ((Element) relevantBindNode).getAttributes();
				for (int i = 0; i < attributes.getLength(); i++) {
					Node attr = attributes.item(i);
					String attrName = attr.getNodeName();
					// we are skiping nodeset attribute because we already rename it as name
					if(!attrName.equals("nodeset")) {
						property.put(attr.getNodeName(), attr.getNodeValue());
					}
				}
			} else {
				property.put("type", "string");
			}


			Node labelNode = targetNode;
			if(targetNode != null) {
				NodeList targetNodeChildren = targetNode.getChildNodes();
				for (int tIndex = 0; tIndex < targetNodeChildren.getLength(); tIndex++) {
					if (targetNodeChildren.item(tIndex).getNodeType() == Node.ELEMENT_NODE) {
						labelNode = targetNodeChildren.item(tIndex);
						break;
					}
				}
			}

			String labelNodeName =  labelNode.getNodeName();
			ObjectNode languages = mapper.createObjectNode();

			//    jr:itext('/sample_form/name:label')
			String labelRefAttr = ((Element) labelNode).getAttribute("ref");

			if (labelRefAttr != null && !labelRefAttr.equals("")) {
				labelRefAttr = labelRefAttr.replace("jr:itext(\'", "");
				labelRefAttr = labelRefAttr.replace("\')", "");

				// Node itextNode = formXML.getElementsByTagName('itext')[0];
				NodeList translationNodes = rootNode.getElementsByTagName("translation");

				for (int i = 0; i < translationNodes.getLength(); i++) {
					Node translationNode = translationNodes.item(i);
					String translationNodeLang = ((Element) translationNode).getAttribute("lang");
					String label = "";

					NodeList translationNodeChildren = translationNode.getChildNodes();
					for (int j = 0; j < translationNodeChildren.getLength(); j++) {
						Node translationNodeChild = translationNodeChildren.item(j);
						if (translationNodeChild.getNodeType() == Node.ELEMENT_NODE) {
							String idAttr = ((Element) translationNodeChild).getAttribute("id");
							if (idAttr.equals(labelRefAttr)) {
								NodeList valueNodes = ((Element) translationNodeChild).getElementsByTagName("value");
								label = valueNodes.item(0).getTextContent();
								break;
							}
						}
					}

					languages.put(translationNodeLang, label);
				}
			// } else if(labelNodeName == "itextId") {
			// 	labelRefAttr = labelNode.textContent;
			// 	const translationChildren = translationNode.children;
			// 	for (let i = 0; i < translationChildren.length; i++) {
			// 		const idAttr = translationChildren[i].getAttribute('id');
			// 		if (idAttr == labelRefAttr) {
			// 			label = translationChildren[i].getElementsByTagName('value')[0].childNodes[0].nodeValue;
			// 			break;
			// 		}
			// 	}
			} else {
				String label = labelNode.getTextContent();
				//if there is no translation then label property will have the label, there will be no lang obj
				property.put("label", label);
			}

			if(languages.size() > 0) {
				property.set("languages", languages);
			}
		} catch (Exception e) {
			property.put("type", "string");
			e.printStackTrace();
		}
		return property;
	}
}
