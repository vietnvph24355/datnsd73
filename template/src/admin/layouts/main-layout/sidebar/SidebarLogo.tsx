import { Link, Typography } from '@mui/material';
import Logo from 'admin/components/icons/brand/Logo';

const SidebarLogo = () => {
  return (
    <Link
      href="/"
      sx={{
        height: 68,
        overflow: 'hidden',
        display: 'flex',
        alignItems: 'center',
        gap: 1.5,
        // pl: theme.spacing(2.5),
      }}
    >
      <Logo fontSize={'large'} />
      <Typography variant="h4">Modernize</Typography>
    </Link>
  );
};

export default SidebarLogo;
