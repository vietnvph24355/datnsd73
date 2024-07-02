type TimeOrders = { time: string; orders: number }[];

export interface ComparisonChartData {
    today: TimeOrders;
    yesterday: TimeOrders;
  }