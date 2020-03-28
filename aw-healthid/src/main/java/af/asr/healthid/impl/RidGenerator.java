package af.asr.healthid.impl;

/**
 * This is an interface for the generation of RID
 */
public interface RidGenerator<T> {
	/**
	 * This method is used to generate Registration ID(RID)
	 * 
	 * @param agentId   input given by user
	 * @param machineId input by user
	 * @return containing generated RID
	 */
	public T generateId(String agentId, String machineId);

	/**
	 * This method is used to generate Registration ID(RID)
	 * 
	 * @param centreId        the center id
	 * @param machineId       the machine id
	 * @param centerIdLength  length of the given center id
	 * @param machineIdLength length of the given machine id
	 * @param sequenceLength  length of the sequence
	 * @param timestampLength length of the timeStamp
	 * @return containing generated RID
	 */
	public T generateId(String centreId, String machineId, int centerIdLength, int machineIdLength, int sequenceLength,
			int timestampLength);

}
