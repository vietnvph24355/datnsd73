import { useState, useEffect } from 'react';
import { Product } from 'admin/data/interface/product';
import { GridColDef } from '@mui/x-data-grid';

const transactionColumnsProduct: GridColDef<Product>[] = [
  {
    field: 'id',
    headerName: 'ID',
    width: 100,
    type: 'string',
  },
  {
    field: 'name',
    headerName: 'Tên sản phẩm',
    width: 100,
    type: 'string',
  },
  {
    field: 'price',
    headerName: 'Giá',
    width: 100,
    type: 'string',
  },
  {
    field: 'quantity',
    headerName: 'Số lượng',
    width: 100,
    type: 'string',
  },
  {
    field: 'image',
    headerName: 'Ảnh',
    width: 100,
    renderCell: (params) => {
      return <img src={params.value} alt="" style={{width:'100px',height:'100px'}}/>;
    },
  },
]

const ProductsPage = () => {
  const [product, setProduct] = useState<Product[]>([])
  useEffect(() => {
    fetch('https://662a087e67df268010a25b8a.mockapi.io/api/v1/user/user')
      .then(response => response.json())
      .then(data => {
        setProduct(data.slice(0, 10))
      })
      .catch(error => {
        console.error('Lỗi:', error);
      });
  }, [])
  console.log(product)
  return (
    <>
      
    </>
  );
};

export default ProductsPage;
