package akkis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewParameter;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewMetadata;

public class AkkisViewHandler extends ViewHandlerWrapper {

	private ViewHandler wrapped;
	
	public AkkisViewHandler(ViewHandler wrapped) {
        this.wrapped = wrapped;
    }
	
	@Override
	public String getActionURL(FacesContext context, String viewId) {
		
		String originalActionURL = super.getActionURL(context, viewId);
		
		String currentViewId = context.getViewRoot().getViewId();
        
		if (viewId.equals(currentViewId))
		{
			String newActionURL = includeViewParamsIfNecessary(context,
	                originalActionURL);
			
			return newActionURL;
		}
		
		return originalActionURL;
	}
	
	private String includeViewParamsIfNecessary(FacesContext context,
            String originalActionURL) {
        String parameterString = "";

        if (true) {

            List<String> ignoreParmasMarker = new ArrayList<>();

            for (String name : context.getExternalContext()
                    .getRequestParameterMap().keySet()) {
                UIComponent component = context.getViewRoot().findComponent(
                        name);
                if (component != null || name.startsWith("input_") || name.startsWith("javax.faces.") || name.startsWith("j_")) {
                    ignoreParmasMarker.add(name);
                }
            }

            for (String key : context.getExternalContext()
                    .getRequestParameterValuesMap().keySet()) {
                if (!ignoreParmasMarker.contains(key)) {
                    if ("".equals(parameterString)) {
                        parameterString = "?";
                    } else {
                        parameterString += "&";
                    }
                    String keyVal = key
                            + "="
                            + context.getExternalContext()
                                    .getRequestParameterMap().get(key);
                    parameterString += keyVal;
                }
            }
        }

        return originalActionURL + parameterString;
	}
	
	@Override
	public ViewHandler getWrapped() {
		return wrapped;
	}

}
