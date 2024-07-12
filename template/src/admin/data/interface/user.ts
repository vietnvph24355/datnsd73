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
const urlApi = 'http://localhost:8081/api/';

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
export { LoginUser, logoutUser };
