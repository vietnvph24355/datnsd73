import { useState, useEffect, ChangeEvent } from 'react';
import { Product } from 'admin/data/interface/product';
import { DataGrid, GridApi, GridColDef, GridSlots, useGridApiRef } from '@mui/x-data-grid';
import { LinearProgress, Stack } from '@mui/material';
import CustomDataGridFooter from 'admin/components/common/table/CustomDataGridFooter';
import CustomDataGridHeader from 'admin/components/common/table/CustomDataGridHeader';
import CustomDataGridNoRows from 'admin/components/common/table/CustomDataGridNoRows';
import SimpleBar from 'simplebar-react';

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
      return <img src={params.value} alt="" style={{ width: '100px', height: '100px' }} />;
    },
  },
];

const ProductsPage = () => {
  const [product, setProduct] = useState<Product[]>([]);
  const [searchText, setSearchText] = useState('');
  const apiRef = useGridApiRef<GridApi>();

  useEffect(() => {
    fetch('https://662a087e67df268010a25b8a.mockapi.io/api/v1/user/user')
      .then((response) => response.json())
      .then((data) => {
        setProduct(data);
      })
      .catch((error) => {
        console.error('Lỗi:', error);
      });
  }, []);

  useEffect(() => {
    apiRef.current.setRows(product);
  }, [apiRef]);

  useEffect(() => {
    apiRef.current.setQuickFilterValues([searchText]);
  }, [searchText, apiRef]);

  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const searchValue = event.currentTarget.value;
    setSearchText(searchValue);
    if (searchValue === '') {
      apiRef.current.setRows(product);
    }
  };
  return (
    <>
      <Stack
        sx={{
          overflow: 'auto',
          // minHeight: 0,
          position: 'relative',
          height: { xs: 'auto', sm: 1 },
          width: 1,
        }}
      >
        <SimpleBar>
          <DataGrid
            autoHeight={false}
            rowHeight={52}
            columns={transactionColumnsProduct}
            loading={false}
            apiRef={apiRef}
            onResize={() => {
              apiRef.current.autosizeColumns({
                includeOutliers: true,
                expand: true,
              });
            }}
            hideFooterSelectedRowCount
            disableColumnResize
            disableColumnMenu
            disableColumnSelector
            disableRowSelectionOnClick
            rowSelection={false}
            slots={{
              loadingOverlay: LinearProgress as GridSlots['loadingOverlay'],
              pagination: CustomDataGridFooter,
              toolbar: CustomDataGridHeader,
              noResultsOverlay: CustomDataGridNoRows,
            }}
            slotProps={{
              toolbar: {
                title: 'Recent Transactions',
                flag: 'user',
                value: searchText,
                onChange: handleChange,
                clearSearch: () => setSearchText(''),
              },
              pagination: { labelRowsPerPage: transactionColumnsProduct.length },
            }}
            initialState={{ pagination: { paginationModel: { page: 1, pageSize: 5 } } }}
            pageSizeOptions={[5, 10, 25]}
            sx={{
              boxShadow: 1,
              px: 3,
              borderColor: 'active.selected',
              height: 1,
              width: 1,
              tableLayout: 'fixed',
            }}
          />
        </SimpleBar>
      </Stack>
    </>
  );
};

export default ProductsPage;
