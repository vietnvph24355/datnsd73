export interface UserRes {
  tokens: string;
  refreshToken: string;
  gmail: string;
  name: string;
  roleId: string;
  acountId: string;
}
export interface LoginData {
  gmail: string;
  password: string;
}

export interface RegisterUser {
  name: string;
  sdt: string;
  gmail: string;
  password: string;
  isActivate: boolean;
  gender: boolean;
  avatar: string;
  roleId: number;
}
const urlApi = 'http://localhost:8081/api/';
// Đăng nhập user
const LoginUser = async (UserLogin: LoginData) => {
  try {
    const response = await fetch(urlApi + 'sign-in', {
      method: 'Post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(UserLogin),
    });
    const data = (await response.json())! as UserRes;
    return data;
  } catch (error) {
    console.log(error.name);
  }
};
// Đăng xuất user
const logoutUser = async () => {
  try {
    const response = await fetch(urlApi + 'logout', {
      method: 'Post',
      headers: {
        'Content-Type': 'application/json',
      },
    }).then((res) => res.text());
    const data = response;
    return data;
  } catch (error) {
    console.log(error.name);
  }
};
// Đăng kí user
const RegisterUser = async (RegisterUser: RegisterUser) => {
  try {
    const response = await fetch(urlApi + 'sign-up', {
      method: 'Post',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(RegisterUser),
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.log(error.name);
  }
};
export { LoginUser, logoutUser, RegisterUser };
