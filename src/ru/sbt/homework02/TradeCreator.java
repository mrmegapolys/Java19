package ru.sbt.homework02;

import ru.sbt.homework02.trades.*;

class TradeCreator {
    static Trade getTrade1(TradeData tradeData) {
        switch (tradeData.getType()) {
            case ("FX_SPOT"):
                return new FXSpot(tradeData.getPrice());
            case ("BOND"):
                return new Bond(tradeData.getPrice());
            case ("COMMODITY_SPOT"):
                return new CommoditySpot(tradeData.getPrice());
            case ("IR_SWAP"):
                return new IRSwap(tradeData.getPrice());
            default:
                throw new RuntimeException("Unsupported type");
        }
    }

    static Trade getTrade2(TradeData tradeData) {
        return TradeType.valueOf(tradeData.getType()).createTrade(tradeData.getPrice());
    }
}
