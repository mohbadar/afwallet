package af.gov.anar.lib.workflow.parser;


import af.gov.anar.lang.infrastructure.exception.api.IncorrectIndexException;
import af.gov.anar.lang.infrastructure.exception.common.IOException;
import af.gov.anar.lib.json.JsonUtility;
import af.gov.anar.lib.json.exception.JsonMappingException;
import af.gov.anar.lib.json.exception.JsonParseException;
import af.gov.anar.lib.workflow.model.Workflow;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class WorkflowParser {

    private static JSONObject jSONObject;

    public JSONObject parse(String json) throws JSONException {
        jSONObject = new JSONObject(json);
        return jSONObject;
    }

    public JSONObject getInitStep() {
        return jSONObject.getJSONArray("steps").getJSONObject(0);
    }


    //get current step + next authorized transitions
    public JSONObject getCurrentStep(String instanceCurrentStep, String workflowJson, List<String> authorities) throws JSONException {

        if(instanceCurrentStep == null || instanceCurrentStep.trim().equals(""))
        {
            return getInitStep();
        }

        JSONObject jsonObject = new JSONObject(workflowJson);
        JSONArray steps = jsonObject.getJSONArray("steps");
        int index= getIndex(instanceCurrentStep);
        JSONObject currentStep = steps.getJSONObject(index);

        JSONArray allStepTransitions =currentStep.getJSONArray("transitions");
        List<String> transitions = convertTransitionsToListOfStrings(allStepTransitions);
        // System.out.println("Transitions=> "+ transitions.toString());
        JSONArray nextSteps = new JSONArray();

        for(String stepName: transitions){
            // System.out.println("Has Authority=> "+ hasAuthorizedGroup(stepName, steps, allGroups));
            if(hasAuthority(stepName, steps, authorities)){
                int stepIndex = getIndex(stepName);
                // System.out.println("NextStep => "+ getTranistion(allStepTransitions, stepName));
                nextSteps.put(getTranistion(allStepTransitions, stepName));
            }
        }

        // System.out.println("AllSteps=> "+ allStepTransitions.toString());
        return  currentStep.put("transitions", nextSteps);
    }

    public Object getTranistion(JSONArray allTransitions, String stepName)
    {
        int index = getTransitionIndex(allTransitions, stepName);
        return allTransitions.get(index);
    }


    public int getTransitionIndex(JSONArray allTransitions, String stepName)
    {
        int index= 0;
        for (int i=0; i < allTransitions.length(); i++)
        {
            JSONObject object = allTransitions.getJSONObject(i);
            String identifier = object.getString("toStep");
            if(stepName.equals(identifier))
            {
                index=i;
            }
        }
        return index;
    }
    //get next transitions
    public JSONArray getNextTransitions(String name, String json)
    {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray steps = jsonObject.getJSONArray("steps");
        int index= getIndex(name, jsonObject);

        if(index == -1)
        {
            throw new IncorrectIndexException();
        }

        JSONObject currentStep = steps.getJSONObject(index);
        JSONArray allStepTransitions =currentStep.getJSONArray("transitions");
        List<String> transitions = convertTransitionsToListOfStrings(allStepTransitions);
        return  currentStep.put("transitions", allStepTransitions).getJSONArray("transitions");
    }

    public JSONArray getSteps()
    {
        return jSONObject.getJSONArray("steps");
    }

    public  int getIndex(String name)
    {
        int index= 0;
        JSONArray steps = jSONObject.getJSONArray("steps");

        for (int i=0; i < steps.length(); i++)
        {
            JSONObject object = steps.getJSONObject(i);
            String identifier = object.getString("name");
            if(name.equals(identifier))
            {
                index=i;
            }
        }
        return index;
    }

    public  int getIndex(String name, JSONObject jsonObject)
    {
        int index= 0;
        JSONArray steps = jsonObject.getJSONArray("steps");

        for (int i=0; i< steps.length(); i++)
        {
            JSONObject object = steps.getJSONObject(i);
            String identifier = object.getString("name");
            if(name.equals(identifier))
            {
                index=i;
            }
        }
        return index;
    }


    private boolean hasAuthority(String stepName, JSONArray steps, List<String> allUserAuthories)
    {

        int index = getIndex(stepName);
        // System.out.println("Suth Index => :"+ index);
        JSONArray stepGroups = steps.getJSONObject(index).getJSONArray("authorizedGroups");
        // System.out.println("Groups => :"+ stepGroups);
        List<String> authorizedGroups = convertAuthorizedGroupsToListOfStrings(stepGroups);
        // System.out.println("authorizedGroups => :"+ authorizedGroups.toString());
        boolean result = false;
        // for(String group: allGroups)
        // {
        for(String ag: authorizedGroups)
        {
            if(allUserAuthories.contains(ag)){
                result= true;
            }
        }
        // }
        return result;
    }

    private List<String> convertAuthorizedGroupsToListOfStrings(JSONArray authorizedGroups)
    {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < authorizedGroups.length(); i++){
            list.add(authorizedGroups.getString(i));
        }
        return list;
    }


    private List<String> convertTransitionsToListOfStrings(JSONArray transitions)
    {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < transitions.length(); i++){
            list.add(transitions.getJSONObject(i).getString("toStep"));
        }
        return list;
    }
}
