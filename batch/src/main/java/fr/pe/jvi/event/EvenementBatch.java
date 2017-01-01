package fr.pe.jvi.event;

public class EvenementBatch
{
	/** L'id du batch*/
	private final long m_batchId;
	
	/** Le nom du batch*/
	private final String m_batchName;
	
	public EvenementBatch(final String p_batchName, final long p_batchId)
	{
		m_batchId = p_batchId;
		m_batchName = p_batchName;
	}

	/**
	 * @return the m_batchId
	 */
	public long getBatchId() {
		return m_batchId;
	}

	/**
	 * @return the m_batchName
	 */
	public String getBatchName() {
		return m_batchName;
	}

	@Override
	public String toString() {
		return "EvenementBatch [m_batchId=" + m_batchId + ", m_batchName="
				+ m_batchName + "]";
	}
}
