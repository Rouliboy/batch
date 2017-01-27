package fr.pe.jvi.event;

public class EvenementSuppressionContexte
{
   /** L'id du batch */
   private final long m_batchId;

   public EvenementSuppressionContexte(final long p_batchId)
   {
      m_batchId = p_batchId;
   }

   /**
    * Renvoie batchId.
    * @return Le batchId.
    */
   public long getBatchId()
   {
      return m_batchId;
   }

}
