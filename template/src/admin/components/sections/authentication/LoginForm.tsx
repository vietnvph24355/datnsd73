import {
  Box,
  Button,
  Checkbox,
  Divider,
  FormControlLabel,
  FormGroup,
  IconButton,
  InputAdornment,
  Link,
  Stack,
  TextField,
  Typography,
} from '@mui/material';
import IconifyIcon from 'admin/components/base/IconifyIcon';
import { ChangeEvent, useState } from 'react';
import { UserLogin } from '/admin/data/interface/user';
import { HienThiUser } from 'api/api-admin/api-login';

const LoginForm = () => {
  const [click, setClick] = useState<UserLogin>({ email: '', password: '' });
  const [showPassword, setShowPassword] = useState(false);
  const handleClickShowPassword = () => {
    setShowPassword((prevShowPassword) => !prevShowPassword);
  };
  const HandleOnchangeForm = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.currentTarget;
    switch (name) {
      case 'Email':
        setClick({ email: value, password: click?.password });
        break;
      case 'Password':
        setClick({ email: click?.email, password: value });
        break;
      default:
        break;
    }
    HienThiUser(click);
  };
  const HandleOnsubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);
    const data: UserLogin = {
      email: formData.get('Email') as string,
      password: formData.get('Password') as string,
    };
    console.log('Username:', data.email);
    console.log('Password:', data.password);
  };
  return (
    <form onSubmit={HandleOnsubmit}>
      <Box
        sx={{
          mt: { sm: 5, xs: 2.5 },
        }}
      >
        <Stack spacing={3}>
          <TextField
            fullWidth
            variant="outlined"
            id="mail"
            type="text"
            label="Email"
            name="Email"
            onChange={HandleOnchangeForm}
          />
          <TextField
            fullWidth
            variant="outlined"
            id="password"
            name="Password"
            type={showPassword ? 'text' : 'password'}
            onChange={HandleOnchangeForm}
            label="Password"
            InputProps={{
              endAdornment: (
                <InputAdornment position="end">
                  <IconButton
                    aria-label="toggle password visibility"
                    onClick={handleClickShowPassword}
                    edge="end"
                  >
                    {showPassword ? (
                      <IconifyIcon icon="el:eye-close" color="action.active" />
                    ) : (
                      <IconifyIcon icon="el:eye-open" color="action.focus" />
                    )}
                  </IconButton>
                </InputAdornment>
              ),
            }}
          />
        </Stack>
        <FormGroup sx={{ my: 2 }}>
          <FormControlLabel
            control={<Checkbox />}
            label="Keep me signed in"
            sx={{
              color: 'text.secondary',
            }}
          />
        </FormGroup>
        <Button
          color="primary"
          variant="contained"
          size="large"
          fullWidth
          // component={Link}
          type="submit"
        >
          Sign In
        </Button>
        <Stack
          sx={{
            textAlign: 'center',
            color: 'text.secondary',
            my: 3,
          }}
        >
          <Link href="/authentication/forgot-password">
            <Typography color="primary" variant="subtitle1">
              Forgot Your Password?
            </Typography>
          </Link>
        </Stack>
        <Divider
          sx={{
            my: 3,
          }}
        />
        <Stack
          spacing={1.5}
          sx={{
            mt: 4,
          }}
        >
          <Typography textAlign="center" color="text.secondary" variant="subtitle1">
            Or sign in using:
          </Typography>
          <Button
            startIcon={<IconifyIcon icon="flat-color-icons:google" />}
            variant="outlined"
            sx={{ typography: { sm: 'button', xs: 'subtitle1', whiteSpace: 'nowrap' } }}
          >
            Continue with Google
          </Button>
          <Button
            startIcon={<IconifyIcon icon="logos:facebook" />}
            variant="outlined"
            sx={{ typography: { sm: 'button', xs: 'subtitle1', whiteSpace: 'nowrap' } }}
          >
            Continue with Facebook
          </Button>
        </Stack>
      </Box>
    </form>
  );
};

export default LoginForm;
