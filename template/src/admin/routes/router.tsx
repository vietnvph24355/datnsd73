import PageLoader from 'admin/components/loading/PageLoader';
import Splash from 'admin/components/loading/Splash';
import AuthLayout from 'admin/layouts/auth-layout';
import { lazy, Suspense } from 'react';
import { createBrowserRouter, Navigate } from 'react-router-dom';
import paths, { rootPaths } from './path';

/* ---------------- Lazy loads various components ------------------------- */
const App = lazy(() => import('App'));
const MainLayout = lazy(() => import('admin/layouts/main-layout'));
const LoginPage = lazy(() => import('admin/pages/authentication/login'));
const SignUpPage = lazy(() => import('admin/pages/authentication/register'));
const ForgotPasswordPage = lazy(() => import('admin/pages/authentication/forgot-password'));
const PasswordResetPage = lazy(() => import('admin/pages/authentication/reset-password'));
const CategoriesPage = lazy(() => import('admin/pages/categories'));
const OrdersPage = lazy(() => import('admin/pages/orders'));
const Dashboard = lazy(() => import('admin/pages/dashboard/index'));
const ProductsPage = lazy(() => import('admin/pages/products'));
const CustomersPage = lazy(() => import('admin/pages/customers'));
const ReportsPage = lazy(() => import('admin/pages/reports'));
const CouponsPage = lazy(() => import('admin/pages/coupons'));
const InboxPage = lazy(() => import('admin/pages/inbox'));
const NotFoundPage = lazy(() => import('admin/pages/not-found'));
/* -------------------------------------------------------------------------- */

/**
 * @Defines the routes for the application using React Router.
 */
export const routes = [
  {
    element: (
      <Suspense fallback={<Splash />}>
        <App />
      </Suspense>
    ),
    children: [
      {
        path: paths.default,
        element: (
          <Suspense fallback={<PageLoader />}>
            <MainLayout />
          </Suspense>
        ),
        children: [
          {
            index: true,
            element: <Dashboard />,
          },
          {
            path: paths.categories,
            element: <CategoriesPage />,
          },
          {
            path: paths.products,
            element: <ProductsPage />,
          },
          {
            path: paths.customers,
            element: <CustomersPage />,
          },
          {
            path: paths.orders,
            element: <OrdersPage />,
          },
          {
            path: paths.reports,
            element: <ReportsPage />,
          },
          {
            path: paths.coupons,
            element: <CouponsPage />,
          },
          {
            path: paths.inbox,
            element: <InboxPage />,
          },
        ],
      },
      {
        path: rootPaths.authRoot,
        element: <AuthLayout />,
        children: [
          {
            path: paths.login,
            element: <LoginPage />,
          },
          {
            path: paths.signup,
            element: <SignUpPage />,
          },
          {
            path: paths.forgotPassword,
            element: <ForgotPasswordPage />,
          },
          {
            path: paths.resetPassword,
            element: <PasswordResetPage />,
          },
        ],
      },
      {
        path: rootPaths.errorRoot,
        children: [
          {
            path: paths.notFound,
            element: <NotFoundPage />,
          },
        ],
      },
      {
        path: '*',
        element: <Navigate to={paths.notFound} replace />,
      },
    ],
  },
];

const router = createBrowserRouter(routes, {
  basename: '/modernize-mui-admin',
});

export default router;
