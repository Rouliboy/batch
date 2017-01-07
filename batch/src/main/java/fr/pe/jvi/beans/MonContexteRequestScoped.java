package fr.pe.jvi.beans;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class MonContexteRequestScoped
{

   private String m_label;

   /**
    * Renvoie m_label.
    * @return Le m_label.
    */
   public String getLabel()
   {
      return m_label;
   }

   /**
    * Attribue le m_label.
    * @param m_label Le m_label
    */
   public void setLabel(final String p_label)
   {
      m_label = p_label;
   }
}
