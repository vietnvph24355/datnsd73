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
import { LoginData, LoginUser } from 'admin/data/interface/user';
import { ChangeEvent, useState } from 'react';
import { toast, ToastContainer } from 'react-toastify';

const LoginForm = () => {
  const [user, setUser] = useState<LoginData>({ email: '', password: '' });
  const [showPassword, setShowPassword] = useState(false);
  const handleClickShowPassword = () => {
    setShowPassword((prevShowPassword) => !prevShowPassword);
  };
  const HandleOnchangeForm = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.currentTarget;
    switch (name) {
      case 'Email':
        setUser({ email: value, password: user?.password });
        break;
      case 'Password':
        setUser({ email: user?.email, password: value });
        break;
      default:
        break;
    }
  };

  const HandelLogin = async () => {
    if (!user.email || !user.password){
      toast.error("Vui lòng nhập Email và Password");
      return;
    }
    let res = await LoginUser(user);
    console.log(res);
    if(res && res.token){
      localStorage.setItem("token", res.token);
    }
  };
  return (
    <form>
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
          onClick={() => HandelLogin()}
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
            onClick={() => HandelLogin()}
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
      <ToastContainer/ >
    </form>
  );
};

export default LoginForm;
