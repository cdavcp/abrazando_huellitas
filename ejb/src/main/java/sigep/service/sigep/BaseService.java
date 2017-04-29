package sigep.service.sigep;

import sigep.data.dao.DaoBase;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.EntityBase;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aleBurgos on 09/02/2015.
 */
public abstract class BaseService<T extends EntityBase, B> {

    private DaoBase<T> daoBase;

    public abstract B findById(Long idEntity);

    public abstract B create(B bean) throws SIGEPException;

    public abstract void update(B bean) throws SIGEPException;

    /*
    Template method que llama a las validaciones de restricciones de modelo y a las validaciones de negocio.
     */
    protected void validate(T entity, Validator validator) throws SIGEPException {
        try {
            constraintValidation(entity, validator);
            bussinessValidation(entity);
        } catch (Exception e) {
            throw new SIGEPException(e);
        }

    }

    /*
    Validación de las restricciones definidas sobre la entity.
     */
    private void constraintValidation(T entity, Validator validator) throws ConstraintViolationException {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
    }

    /*
    Validaciones de negocio (se definen en las clases hijas). Si alguna restricción de negocio no se cumple lanzar ValidationException.
     */
    public abstract void bussinessValidation(T entity) throws SIGEPValidationException;

}
