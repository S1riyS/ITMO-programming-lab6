package s1riys.lab6.common.network.requests;

import s1riys.lab6.common.constants.Commands;

public class AdvancedAggregationRequest extends Request {
    public final long minPrice;
    public final Double minAnnualTurnover;
    public final Double maxAnnualTurnover;
    public AdvancedAggregationRequest(long minPrice, Double minAnnualTurnover, Double maxAnnualTurnover) {
        super(Commands.ADVANCED_AGGREGATION);
        this.minPrice = minPrice;
        this.minAnnualTurnover = minAnnualTurnover;
        this.maxAnnualTurnover = maxAnnualTurnover;
    }
}
