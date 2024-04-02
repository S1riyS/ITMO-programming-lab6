package s1riys.lab6.common.network.responses;

import s1riys.lab6.common.constants.Commands;
import s1riys.lab6.common.managers.CommandManager;

public class AdvancedAggregationResponse extends Response {
    public final Double averageDistance;

    public AdvancedAggregationResponse(Double averageDistance, String error) {
        super(Commands.ADVANCED_AGGREGATION, error);
        this.averageDistance = averageDistance;
    }
}
