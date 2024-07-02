import { CssBaseline, ThemeProvider } from '@mui/material';
import BreakpointsProvider from 'admin/providers/useBreakPoint';
import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import router from 'admin/routes/router';
import { theme } from 'admin/theme/theme';
import './index.css';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <BreakpointsProvider>
        <CssBaseline />
        <RouterProvider router={router} />
      </BreakpointsProvider>
    </ThemeProvider>
  </React.StrictMode>,
);
