package matheus.reichemback.stang.exception;


public class EntityNotFoundException extends RuntimeException{
	
	public EntityNotFoundException(Class<?> clazz, String textOfError) {
		super(EntityNotFoundException.generateMessage(clazz, textOfError));
	}

	private static String generateMessage(Class<?> clazz, String textOfError) {
		String className = clazz.getSimpleName();
		String message = (className + " " + textOfError);
		return message;
	}
}
