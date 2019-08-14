package controller;

import domain.PersonService;

public class ControllerFactory {
	
    public RequestHandler getController(String key, PersonService personService) {
        return createHandler(key, personService);
    }
    
	private RequestHandler createHandler(String handlerName, PersonService personService) {
		RequestHandler handler = null;
		try {
			Class<?> handlerClass = Class.forName("controller."+ handlerName);
			Object handlerObject = handlerClass.newInstance();
			handler = (RequestHandler) handlerObject;
	    	handler.setModel(personService);
		} catch (Exception e) {
			throw new RuntimeException("Pagina onbestaand");
		}
		return handler;
	}


}
