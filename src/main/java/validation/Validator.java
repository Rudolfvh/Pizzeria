package validation;


public interface Validator<T> {
    ValidatorResult isValid(T object);
}
