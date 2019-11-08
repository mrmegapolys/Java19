package ru.sbt.homework02;

import ru.sbt.homework02.trades.*;

class TradeCreator {
    static Trade getTrade1(TradeData tradeData) {
        Trade trade;

        switch (tradeData.getType()) {
            case ("FX_SPOT"):
                trade = new FXSpot(tradeData.getPrice());
                break;
            case ("BOND"):
                trade = new Bond(tradeData.getPrice());
                break;
            case ("COMMODITY_SPOT"):
                trade = new CommoditySpot(tradeData.getPrice());
                break;
            case ("IR_SWAP"):
                trade = new IRSwap(tradeData.getPrice());
                break;
            default:
                trade = null;
        }
        return trade;
    }

    static Trade getTrade2(TradeData tradeData) {
        return TradeType.valueOf(tradeData.getType()).createTrade(tradeData.getPrice());
    }

}
