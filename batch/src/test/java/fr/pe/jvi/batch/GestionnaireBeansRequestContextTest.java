// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test de la classe {@link GestionnaireBeansRequestContext}
 * @author julien
 */
public class GestionnaireBeansRequestContextTest
{

   @Test
   public void test()
   {
      final CleRequestContext cle = new CleRequestContext(getClass(), 1L);
      final CleRequestContext cle2 = new CleRequestContext(getClass(), 1L);
      final CleRequestContext cle3 = new CleRequestContext(getClass(), 1L);

      // reflexive
      assertTrue(cle.equals(cle));
      assertTrue(cle.hashCode() == cle.hashCode());

      // Transitive
      assertTrue(cle.equals(cle2));
      assertTrue(cle.hashCode() == cle2.hashCode());
      assertTrue(cle2.equals(cle3));
      assertTrue(cle2.hashCode() == cle3.hashCode());
      assertTrue(cle.equals(cle3));
      assertTrue(cle.hashCode() == cle3.hashCode());

      // Symetrique
      assertTrue(cle.equals(cle2));
      assertTrue(cle2.equals(cle));

      // Autre
      assertFalse(cle.equals(null));

      final CleRequestContext cleAutre = new CleRequestContext((new Object()).getClass(), 1L);
      final CleRequestContext cleAutre2 = new CleRequestContext(getClass(), 2L);

      assertFalse(cleAutre.equals(cle));
      assertFalse(cle.equals(cleAutre));
      assertFalse(cleAutre2.equals(cle));
      assertFalse(cle.equals(cleAutre2));
      assertFalse(cleAutre2.equals(cleAutre));
      assertFalse(cleAutre.equals(cleAutre2));
   }
}
