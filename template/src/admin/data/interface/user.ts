export interface UserLogin {
  id: string;
  email: string;
  password: string;
  first_name: string;
  last_name: string;
  avatar: string;
}
export interface LoginData {
  email: string;
  password: string;
}  

const LoginUser = async (UserLogin: LoginData) => {
  try {
    const response = await fetch('https://reqres.in/api/login', {
      method: "Post",
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(UserLogin)
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.log(error.message);
  }
}

export {LoginUser}
