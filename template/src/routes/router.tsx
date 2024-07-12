import PageLoader from 'components/loading/PageLoader';
import Splash from 'components/loading/Splash';
import AuthLayout from 'layouts/auth-layout';
import { lazy, Suspense } from 'react';
import { createBrowserRouter, Navigate } from 'react-router-dom';
import paths, { rootPaths } from './path';

/* ---------------- Lazy loads various components ------------------------- */
const App = lazy(() => import('App'));
const MainLayout = lazy(() => import('layouts/main-layout'));
const LoginPage = lazy(() => import('pages/admin/authentication/login'));
const SignUpPage = lazy(() => import('pages/admin/authentication/register'));
const ForgotPasswordPage = lazy(() => import('pages/admin/authentication/forgot-password'));
const PasswordResetPage = lazy(() => import('pages/admin/authentication/reset-password'));
const CategoriesPage = lazy(() => import('pages/admin/categories'));
const OrdersPage = lazy(() => import('pages/admin/orders'));
const Dashboard = lazy(() => import('pages/admin/dashboard/index'));
const ProductsPage = lazy(() => import('pages/admin/products'));
const CustomersPage = lazy(() => import('pages/admin/customers'));
const ReportsPage = lazy(() => import('pages/admin/reports'));
const CouponsPage = lazy(() => import('pages/admin/coupons'));
const InboxPage = lazy(() => import('pages/admin/inbox'));
const NotFoundPage = lazy(() => import('pages/admin/not-found'));
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
  basename: '/bee-shop',
});

export default router;
