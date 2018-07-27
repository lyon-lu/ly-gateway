/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * Item.java created on Jul 25, 2018 2:28:43 PM by Lyon Lu 
 */
package com.study.gateway.jaxb.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 25, 2018 2:28:43 PM
 *
 * </pre>
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Item implements Serializable
{
    /**
    * @fields serialVersionUID : TODO
    */
    private static final long serialVersionUID = -4995773540997165156L;
    
    private String SkuNo;
    private String ItemName;
    private String Description;
    private String StandardDescription;
    private String Qc;
    private BigDecimal Height;
    private BigDecimal Width;
    private BigDecimal Length;
    private BigDecimal Weight;
    private String QuantityUm;
    private String QualityProportion;
    private String IsPresent;
    private String IsVirtualProduct;
    private String TransportProperty;
    private String IsFragile;
    private String ItemClass;
    private String ItemStyle;
    private String ItemColor;
    private String ItemSize;
    private String StorageTemperature;
    private String TransportTemperature;
    private String ExpirationDateDays;
    private String ShelfLife;
    private String Qtymin;
    private String LotTemplate;
    private String SerialNumTrackInbound;
    private String SerialNumTrackOutbound;
    private String SerialNumTrackInventory;
    private String BomAction;
    private String ItemClassify1;
    private String ItemClassify2;
    private String ItemClassify3;
    private BarCode BarCode;
    private ItemCategory ItemCategory;
    
    @XmlElementWrapper(name="Containers") 
    private List<Container> Container;
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class BarCode
    {
        private String BarCode1;
        private String BarCode2;
        private String BarCode3;
        private String BarCode4;
        public String getBarCode1()
        {
            return BarCode1;
        }
        public void setBarCode1(String barCode1)
        {
            BarCode1 = barCode1;
        }
        public String getBarCode2()
        {
            return BarCode2;
        }
        public void setBarCode2(String barCode2)
        {
            BarCode2 = barCode2;
        }
        public String getBarCode3()
        {
            return BarCode3;
        }
        public void setBarCode3(String barCode3)
        {
            BarCode3 = barCode3;
        }
        public String getBarCode4()
        {
            return BarCode4;
        }
        public void setBarCode4(String barCode4)
        {
            BarCode4 = barCode4;
        }
        
        
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ItemCategory
    {
        private String ItemCategory1;
        private String ItemCategory2;
        private String ItemCategory3;
        private String ItemCategory4;
        public String getItemCategory1()
        {
            return ItemCategory1;
        }
        public void setItemCategory1(String itemCategory1)
        {
            ItemCategory1 = itemCategory1;
        }
        public String getItemCategory2()
        {
            return ItemCategory2;
        }
        public void setItemCategory2(String itemCategory2)
        {
            ItemCategory2 = itemCategory2;
        }
        public String getItemCategory3()
        {
            return ItemCategory3;
        }
        public void setItemCategory3(String itemCategory3)
        {
            ItemCategory3 = itemCategory3;
        }
        public String getItemCategory4()
        {
            return ItemCategory4;
        }
        public void setItemCategory4(String itemCategory4)
        {
            ItemCategory4 = itemCategory4;
        }
        
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Container
    {
        private String PackUm;
        private String UmDescr;
        private String ConversionQty;
        private BigDecimal Height;
        private BigDecimal Width;
        private BigDecimal Length;
        private String DimensionUm;
        private BigDecimal Weight;
        private String WeightUm;
        private String UserDef1;
        private String UserDef2;
        private String UserDef3;
        private String UserDef4;
        public String getPackUm()
        {
            return PackUm;
        }
        public void setPackUm(String packUm)
        {
            PackUm = packUm;
        }
        public String getUmDescr()
        {
            return UmDescr;
        }
        public void setUmDescr(String umDescr)
        {
            UmDescr = umDescr;
        }
        public String getConversionQty()
        {
            return ConversionQty;
        }
        public void setConversionQty(String conversionQty)
        {
            ConversionQty = conversionQty;
        }
        public BigDecimal getHeight()
        {
            return Height;
        }
        public void setHeight(BigDecimal height)
        {
            Height = height;
        }
        public BigDecimal getWidth()
        {
            return Width;
        }
        public void setWidth(BigDecimal width)
        {
            Width = width;
        }
        public BigDecimal getLength()
        {
            return Length;
        }
        public void setLength(BigDecimal length)
        {
            Length = length;
        }
        public String getDimensionUm()
        {
            return DimensionUm;
        }
        public void setDimensionUm(String dimensionUm)
        {
            DimensionUm = dimensionUm;
        }
        public BigDecimal getWeight()
        {
            return Weight;
        }
        public void setWeight(BigDecimal weight)
        {
            Weight = weight;
        }
        public String getWeightUm()
        {
            return WeightUm;
        }
        public void setWeightUm(String weightUm)
        {
            WeightUm = weightUm;
        }
        public String getUserDef1()
        {
            return UserDef1;
        }
        public void setUserDef1(String userDef1)
        {
            UserDef1 = userDef1;
        }
        public String getUserDef2()
        {
            return UserDef2;
        }
        public void setUserDef2(String userDef2)
        {
            UserDef2 = userDef2;
        }
        public String getUserDef3()
        {
            return UserDef3;
        }
        public void setUserDef3(String userDef3)
        {
            UserDef3 = userDef3;
        }
        public String getUserDef4()
        {
            return UserDef4;
        }
        public void setUserDef4(String userDef4)
        {
            UserDef4 = userDef4;
        }
    }

    public String getSkuNo()
    {
        return SkuNo;
    }

    public void setSkuNo(String skuNo)
    {
        SkuNo = skuNo;
    }

    public String getItemName()
    {
        return ItemName;
    }

    public void setItemName(String itemName)
    {
        ItemName = itemName;
    }

    public String getDescription()
    {
        return Description;
    }

    public void setDescription(String description)
    {
        Description = description;
    }

    public String getStandardDescription()
    {
        return StandardDescription;
    }

    public void setStandardDescription(String standardDescription)
    {
        StandardDescription = standardDescription;
    }

    public String getQc()
    {
        return Qc;
    }

    public void setQc(String qc)
    {
        Qc = qc;
    }

    public BigDecimal getHeight()
    {
        return Height;
    }

    public void setHeight(BigDecimal height)
    {
        Height = height;
    }

    public BigDecimal getWidth()
    {
        return Width;
    }

    public void setWidth(BigDecimal width)
    {
        Width = width;
    }

    public BigDecimal getLength()
    {
        return Length;
    }

    public void setLength(BigDecimal length)
    {
        Length = length;
    }

    public BigDecimal getWeight()
    {
        return Weight;
    }

    public void setWeight(BigDecimal weight)
    {
        Weight = weight;
    }

    public String getQuantityUm()
    {
        return QuantityUm;
    }

    public void setQuantityUm(String quantityUm)
    {
        QuantityUm = quantityUm;
    }

    public String getQualityProportion()
    {
        return QualityProportion;
    }

    public void setQualityProportion(String qualityProportion)
    {
        QualityProportion = qualityProportion;
    }

    public String getIsPresent()
    {
        return IsPresent;
    }

    public void setIsPresent(String isPresent)
    {
        IsPresent = isPresent;
    }

    public String getIsVirtualProduct()
    {
        return IsVirtualProduct;
    }

    public void setIsVirtualProduct(String isVirtualProduct)
    {
        IsVirtualProduct = isVirtualProduct;
    }

    public String getTransportProperty()
    {
        return TransportProperty;
    }

    public void setTransportProperty(String transportProperty)
    {
        TransportProperty = transportProperty;
    }

    public String getIsFragile()
    {
        return IsFragile;
    }

    public void setIsFragile(String isFragile)
    {
        IsFragile = isFragile;
    }

    public String getItemClass()
    {
        return ItemClass;
    }

    public void setItemClass(String itemClass)
    {
        ItemClass = itemClass;
    }

    public String getItemStyle()
    {
        return ItemStyle;
    }

    public void setItemStyle(String itemStyle)
    {
        ItemStyle = itemStyle;
    }

    public String getItemColor()
    {
        return ItemColor;
    }

    public void setItemColor(String itemColor)
    {
        ItemColor = itemColor;
    }

    public String getItemSize()
    {
        return ItemSize;
    }

    public void setItemSize(String itemSize)
    {
        ItemSize = itemSize;
    }

    public String getStorageTemperature()
    {
        return StorageTemperature;
    }

    public void setStorageTemperature(String storageTemperature)
    {
        StorageTemperature = storageTemperature;
    }

    public String getTransportTemperature()
    {
        return TransportTemperature;
    }

    public void setTransportTemperature(String transportTemperature)
    {
        TransportTemperature = transportTemperature;
    }

    public String getExpirationDateDays()
    {
        return ExpirationDateDays;
    }

    public void setExpirationDateDays(String expirationDateDays)
    {
        ExpirationDateDays = expirationDateDays;
    }

    public String getShelfLife()
    {
        return ShelfLife;
    }

    public void setShelfLife(String shelfLife)
    {
        ShelfLife = shelfLife;
    }

    public String getQtymin()
    {
        return Qtymin;
    }

    public void setQtymin(String qtymin)
    {
        Qtymin = qtymin;
    }

    public String getLotTemplate()
    {
        return LotTemplate;
    }

    public void setLotTemplate(String lotTemplate)
    {
        LotTemplate = lotTemplate;
    }

    public String getSerialNumTrackInbound()
    {
        return SerialNumTrackInbound;
    }

    public void setSerialNumTrackInbound(String serialNumTrackInbound)
    {
        SerialNumTrackInbound = serialNumTrackInbound;
    }

    public String getSerialNumTrackOutbound()
    {
        return SerialNumTrackOutbound;
    }

    public void setSerialNumTrackOutbound(String serialNumTrackOutbound)
    {
        SerialNumTrackOutbound = serialNumTrackOutbound;
    }

    public String getSerialNumTrackInventory()
    {
        return SerialNumTrackInventory;
    }

    public void setSerialNumTrackInventory(String serialNumTrackInventory)
    {
        SerialNumTrackInventory = serialNumTrackInventory;
    }

    public String getBomAction()
    {
        return BomAction;
    }

    public void setBomAction(String bomAction)
    {
        BomAction = bomAction;
    }

    public String getItemClassify1()
    {
        return ItemClassify1;
    }

    public void setItemClassify1(String itemClassify1)
    {
        ItemClassify1 = itemClassify1;
    }

    public String getItemClassify2()
    {
        return ItemClassify2;
    }

    public void setItemClassify2(String itemClassify2)
    {
        ItemClassify2 = itemClassify2;
    }

    public String getItemClassify3()
    {
        return ItemClassify3;
    }

    public void setItemClassify3(String itemClassify3)
    {
        ItemClassify3 = itemClassify3;
    }

    public BarCode getBarCode()
    {
        return BarCode;
    }

    public void setBarCode(BarCode barCode)
    {
        BarCode = barCode;
    }

    public ItemCategory getItemCategory()
    {
        return ItemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory)
    {
        ItemCategory = itemCategory;
    }

    public List<Container> getContainer()
    {
        return Container;
    }

    public void setContainer(List<Container> container)
    {
        Container = container;
    }
}
