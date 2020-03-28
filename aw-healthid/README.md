#### Health ID Generator Library

```properties


#-----------------------------Registration-----------------------------------------------
#length of the registration center id
healthid.registrationcenterid.length=5

#-----------------------------Machine Id -----------------------------------------------
#length of the machine id
healthid.machineid.length=5

#-----------------------------RID Properties---------------------------------------
# length of the rid
healthid.rid.length=29
# length of the timestamp
healthid.rid.timestamp-length=14
# rid sequence max digits
healthid.rid.sequence-length=5

```

1.CenterId of the registration center as string of size metion in property.

2.machine id of the device as string of size metion in property.

For example: centerId="32345" and machineId="56789".

The response will be numeric string of desire size with centerId, dongleId, five digit sequence generated numbers and timestamp in format "yyyymmddhhmmss" of 14 digits.

#### Usage

```
@Autowired
RidGenerator <String> ridGeneratorImpl;

String rid = ridGeneratorImpl.generateId("34532","67897");
```