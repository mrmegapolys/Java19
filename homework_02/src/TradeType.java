import trades.*;

public enum TradeType {
    FX_SPOT {
        @Override
        public Trade createTrade(double price) {
            return new FXSpot(price);
        }
    },
    BOND {
        @Override
        public Trade createTrade(double price) {
            return new Bond(price);
        }
    },
    COMMODITY_SPOT {
        @Override
        public Trade createTrade(double price) {
            return new CommoditySpot(price);
        }
    },
    IR_SWAP {
        @Override
        public Trade createTrade(double price) {
            return new IRSwap(price);
        }
    };

    public abstract Trade createTrade(double price);
}
