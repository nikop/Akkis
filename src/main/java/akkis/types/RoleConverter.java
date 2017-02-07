package akkis.types;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="roleConverter")
public class RoleConverter extends EnumConverter {

    public RoleConverter() {
        super(Role.class);
    }

}