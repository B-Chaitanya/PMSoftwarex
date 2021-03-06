package org.tarak.pms.utils;

import java.util.LinkedList;
import java.util.List;

import org.tarak.pms.models.GoodsReceiveChallan;
import org.tarak.pms.models.GoodsReceiveChallanItem;
import org.tarak.pms.models.PurchaseOrder;
import org.tarak.pms.models.PurchaseOrderItem;

public class GoodsReceiveChallanUtils {
	public static GoodsReceiveChallan populateGoodsReceiveChallan(PurchaseOrder purchaseOrder,GoodsReceiveChallan goodsReceiveChallan)
	{
		List<GoodsReceiveChallanItem> goodsReceiveChallanItems=populateGoodsReceiveChallanItems(purchaseOrder.getPurchaseOrderItems());
		goodsReceiveChallan.setPurchaseOrder(purchaseOrder);
		double totalCost=0;
		for(GoodsReceiveChallanItem goodsReceiveChallanItem: goodsReceiveChallanItems)
		{
			totalCost+=goodsReceiveChallanItem.getRate()*goodsReceiveChallanItem.getQuantity();
		}
		goodsReceiveChallan.setTotalCost(totalCost);
		goodsReceiveChallan.setGoodsReceiveChallanItems(goodsReceiveChallanItems);
		goodsReceiveChallan.setFinYear(purchaseOrder.getFinYear());
		goodsReceiveChallan.setVendor(purchaseOrder.getVendor());
		return goodsReceiveChallan;
	}

	public static List<GoodsReceiveChallanItem> populateGoodsReceiveChallanItems(List<PurchaseOrderItem> purchaseOrderItems) 
	{
		List<GoodsReceiveChallanItem> goodsReceiveChallanItems=new LinkedList<GoodsReceiveChallanItem>(); 
		for(PurchaseOrderItem purchaseOrderItem: purchaseOrderItems)
		{
			GoodsReceiveChallanItem goodsReceiveChallanItem=new GoodsReceiveChallanItem();
			goodsReceiveChallanItem.setBrand(purchaseOrderItem.getBrand());
			goodsReceiveChallanItem.setCategory(purchaseOrderItem.getCategory());
			goodsReceiveChallanItem.setDescription(purchaseOrderItem.getDescription());
			goodsReceiveChallanItem.setFinYear(purchaseOrderItem.getFinYear());
			goodsReceiveChallanItem.setMeasurement(purchaseOrderItem.getMeasurement());
			goodsReceiveChallanItem.setQuantity(purchaseOrderItem.getQuantity());
			goodsReceiveChallanItem.setRate(purchaseOrderItem.getRate());
			goodsReceiveChallanItem.setSrNo(purchaseOrderItem.getSrNo());
			goodsReceiveChallanItem.setStyle(purchaseOrderItem.getStyle());
			goodsReceiveChallanItem.setVariant(purchaseOrderItem.getVariant());
			goodsReceiveChallanItems.add(goodsReceiveChallanItem);
		}
		return goodsReceiveChallanItems;
	}
	

}
