package service;


import dao.OrderDao;
import dto.CreateOrderDto;
import mapper.CreateOrderMapper;
import mapper.OrderMapper;

public class OrderService {

    private static final OrderService INSTANCE = new OrderService();
    private final OrderDao orderDao = OrderDao.getInstance();
    private final CreateOrderMapper createOrderMapper = CreateOrderMapper.getInstance();
    private final OrderMapper orderMapper = OrderMapper.getInstance();

    public Integer create(CreateOrderDto createOrderDto) {

        var orderEntity = createOrderMapper.mapFrom(createOrderDto);
        orderDao.save(orderEntity);
        return orderEntity.getOrderid();
    }

    public static OrderService getInstance() {
        return INSTANCE;
    }
}
