/* eslint-disable @typescript-eslint/no-explicit-any */
import { SvgIconProps } from '@mui/material';
import CartIcon from 'admin/components/icons/menu-icons/CartIcon';
import CategoriesIcon from 'admin/components/icons/menu-icons/CategoriesIcon';
import CouponsIcon from 'admin/components/icons/menu-icons/CouponsIcon';
import CustomersIcon from 'admin/components/icons/menu-icons/CustomersIcon';
// import ForgotPasswordIcon from 'admin/components/icons/menu-icons/ForgotPasswordIcon';
// import GlobalSettingsIcon from 'admin/components/icons/menu-icons/GlobalSettingsIcon';
import HomeIcon from 'admin/components/icons/menu-icons/HomeIcon';
// import InboxIcon from 'admin/components/icons/menu-icons/InboxIcon';
import ProductUpdateIcon from 'admin/components/icons/menu-icons/ProductUpdateIcon';
import OrderIcon from 'admin/components/icons/menu-icons/OrderIcon';
// import PersonalSettingsIcon from 'admin/components/icons/menu-icons/PersonalSettingsIcon';
import ProductsIcon from 'admin/components/icons/menu-icons/ProductsIcon';
// import ReportsIcon from 'admin/components/icons/menu-icons/ReportsIcon';
// import ResetPasswordIcon from 'admin/components/icons/menu-icons/ResetPasswordIcon';
// import SignUpIcon from 'admin/components/icons/menu-icons/SignInIcon';
// import SignInIcon from 'admin/components/icons/menu-icons/SignUpIcon';

import { uniqueId } from 'lodash';
import SignInIcon from 'admin/components/icons/menu-icons/SignInIcon';

export interface IMenuitems {
  [x: string]: any;
  id?: string;
  navlabel?: boolean;
  subheader?: string;
  title?: string;
  icon?: (props: SvgIconProps) => JSX.Element;
  href?: string;
  children?: IMenuitems[];
  chip?: string;
  chipColor?: string | any;
  variant?: string | any;
  available?: boolean;
  level?: number;
  onClick?: React.MouseEvent<HTMLButtonElement, MouseEvent>;
}

const Menuitems: IMenuitems[] = [
  {
    id: uniqueId(),
    title: 'Dashboard',
    icon: HomeIcon,
    href: '/',
    available: true,
  },
  {
    id: uniqueId(),
    title: 'Bán hàng tại quầy',
    icon: CartIcon,
    href: '/orders',
    chipColor: 'secondary',
    available: true,
    chip: '16',
  },
  {
    id: uniqueId(),
    title: 'Products',
    icon: ProductUpdateIcon,
    available: true,
    children: [
      {
        id: uniqueId(),
        title: 'Quản lý sản phẩm',
        icon: ProductsIcon,
        href: '/products',
        available: true,
      },
      {
        id: uniqueId(),
        title: 'Quản lý thuộc tính phụ',
        icon: CategoriesIcon,
        href: '/categories',
        available: true,
      },
    ],
  },
  {
    id: uniqueId(),
    title: 'User',
    icon: CustomersIcon,
    href: '/customers',
    available: true,
  },
  {
    id: uniqueId(),
    title: 'Quản lý hóa đơn',
    icon: OrderIcon,
    href: '/reports',
    available: true,
  },
  {
    id: uniqueId(),
    title: 'Coupons',
    icon: CouponsIcon,
    href: '/coupons',
    available: true,
  },
  // {
  //   id: uniqueId(),
  //   title: 'Inbox',
  //   icon: InboxIcon,
  //   href: '/inbox',
  //   available: true,
  // },
  // {
  //   navlabel: true,
  //   subheader: 'Authentication',
  // },
  // {
  //   id: uniqueId(),
  //   title: 'Sign Up',
  //   icon: SignUpIcon,
  //   href: '/authentication/sign-up',
  //   available: true,
  // },
  {
    id: uniqueId(),
    title: 'Sign In',
    icon: SignInIcon,
    href: '/authentication/login',
    available: true,
  },
  // {
  //   id: uniqueId(),
  //   title: 'Forgot Password',
  //   icon: ForgotPasswordIcon,
  //   href: '/authentication/forgot-password',
  //   available: true,
  // },
  // {
  //   id: uniqueId(),
  //   title: 'Reset Password',
  //   icon: ResetPasswordIcon,
  //   href: '/authentication/reset-password',
  //   available: true,
  // },

  // {
  //   navlabel: true,
  //   subheader: 'Settings',
  // },
  // {
  //   id: uniqueId(),
  //   title: 'Personal Settings',
  //   icon: PersonalSettingsIcon,
  //   href: '/settings/#!',
  //   available: false,
  // },
  // {
  //   id: uniqueId(),
  //   title: 'Global Settings',
  //   icon: GlobalSettingsIcon,
  //   href: '/settings/#!',
  //   available: false,
  // },
];

export default Menuitems;
