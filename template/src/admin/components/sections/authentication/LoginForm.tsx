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
import { useNavigate } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';

const LoginForm = () => {
  const [user, setUser] = useState<LoginData>({ gmail: '', password: '' });
  const [showPassword, setShowPassword] = useState(false);
  const [errGmail, setErrGmail] = useState('');
  const [loadingLogin, setLoadingLogin] = useState(false);
  const navigate = useNavigate();
  const handleClickShowPassword = () => {
    setShowPassword((prevShowPassword) => !prevShowPassword);
  };
  const HandleOnchangeForm = (e: ChangeEvent<HTMLInputElement>) => {
    const pattenGmail = /^[a-zA-Z0-9._%-]+@gmail.com$/;
    const { name, value } = e.currentTarget;
    switch (name) {
      case 'Email':
        if (!value.match(new RegExp(pattenGmail))) {
          setErrGmail('Định dạng gmail không chính xác');
        } else {
          setUser({ gmail: value, password: user?.password });
          setErrGmail('');
        }
        break;
      case 'Password':
        setUser({ gmail: user?.gmail, password: value });
        break;
      default:
        break;
    }
  };
  const HandelLogin = async () => {
    if (!user.gmail || !user.password) {
      toast.error('Vui lòng nhập Email và Password');
      return;
    }
    setLoadingLogin(true);
    const res = await LoginUser(user);
    if (res && res.tokens) {
      sessionStorage.setItem('token', res.tokens);
      sessionStorage.setItem('user', JSON.stringify(res));
      await navigate('/');
    } else {
      toast.error('Thông tin tài khoản hoặc mật khẩu không chính xác');
    }
    setLoadingLogin(false);
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
          {errGmail && <p style={{ color: 'red' }}>{errGmail}</p>}
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
          disabled={loadingLogin}
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
      <ToastContainer />
    </form>
  );
};

export default LoginForm;
