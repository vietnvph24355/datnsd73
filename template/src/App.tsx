// import { ReactNode, useEffect } from 'react';
import { Outlet } from 'react-router-dom';

// interface ProtectedRouteProps {
//   children: ReactNode;
// }

// const ProtectedRoute = ({ children }: ProtectedRouteProps) => {
//   const navigate = useNavigate();
//   const location = useLocation();

//   useEffect(() => {
//     const user = sessionStorage.getItem('user');
//     const token = sessionStorage.getItem('token');

//     if (!user || !token) {
//       navigate('/authentication/login');
//     } else if (location.pathname === '/authentication/login') {
//       navigate('/');
//     }
//   }, [navigate, location.pathname]);

//   return children;
// };

const App = () => {
  return (
    <>
      {/* <ProtectedRoute> */}
      <Outlet />
      {/* </ProtectedRoute> */}
    </>
  );
};

export default App;
