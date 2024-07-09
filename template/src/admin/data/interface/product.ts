export interface Product {
  id: string;
  name: string;
  price: number;
  image: string;
  quantity: number;
}

class MyObject implements Product {
  name: string;
  price: number;
  quantity: number;
  image: string;
  id: string;

  constructor(obj: Product) {
    this.name = obj.name;
    this.price = obj.price;
    this.quantity = obj.quantity;
    this.image = obj.image;
    this.id = obj.id;
  }
}

export const GetAllProducts = fetch('https://662a087e67df268010a25b8a.mockapi.io/api/v1/user/user')
  .then((response) => response.json())
  .then((data) => {
    const myObjects: MyObject[] = data.map((item: Product) => new MyObject(item));
    return myObjects;
  })
  .catch((error) => {
    console.error('Lỗi:', error);
  });
