package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.models.Organization;

public class FilterByManufacturerRequest extends Request {
    public final Organization organization;
    public FilterByManufacturerRequest(Organization organization) {
        super(Commands.FILTER_BY_MANUFACTURER);
        this.organization = organization;
    }
}
