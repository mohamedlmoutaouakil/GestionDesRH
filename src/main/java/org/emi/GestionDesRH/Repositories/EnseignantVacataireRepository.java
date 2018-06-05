package org.emi.GestionDesRH.Repositories;

import org.emi.GestionDesRH.entities.EnseignantVacataire;
import org.emi.GestionDesRH.entities.QEnseignantVacataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import java.lang.String;

public interface EnseignantVacataireRepository extends JpaRepository<EnseignantVacataire, Long> , QuerydslPredicateExecutor<EnseignantVacataire> , QuerydslBinderCustomizer<QEnseignantVacataire> {

	public EnseignantVacataire findByEmailProfessionnel(String emailprofessionnel);
	
	@Override
    default public void customize(
      QuerydslBindings bindings, QEnseignantVacataire root) {
        bindings.bind(String.class)
          .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(root.emailPersonnel);
        bindings.excluding(root.emailProfessionnel);
      }
	
	@Query("SELECT motDePasse FROM Enseignant  WHERE id = :enseignantId")
	public String getPasswordById(@Param("enseignantId") Long enseignantId);
	
}