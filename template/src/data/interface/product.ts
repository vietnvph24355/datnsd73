export interface Product {
  id: number;
  avatar: string;
  name: string;
  descriptions: string;
  dateCreate: Date;
  dateUpdate: Date;
  status: number;
  idNsx: number;
  idLoaiGiay: number;
  idChatLieu: number;
  idDeGiay: number;
}
const urlApi = 'http://localhost:8081/api/';
// export const GetAllProducts = fetch(urlApi+'products')
//   .then((response) => response.json())
//   .then((data) => {
//     const myObjects: Product[] = data.map((item: Product) => item);
//     return myObjects;
//   })
//   .catch((error) => {
//     console.error('Lỗi:', error);
//   });

const GetAllProducts = async (token: any) => {
  try {
    const response = await fetch(urlApi + 'products', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + token,
      },
    }).then((res) => res.json());
    const data = response;
    return data;
  } catch (error) {
    console.log(error.name);
  }
};

export { GetAllProducts };
