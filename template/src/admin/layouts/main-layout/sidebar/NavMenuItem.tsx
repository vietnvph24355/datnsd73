import {
  Box,
  Chip,
  Collapse,
  IconButton,
  Link,
  List,
  ListItemButton,
  ListItemIcon,
  ListItemSecondaryAction,
  ListItemText,
  Typography,
} from '@mui/material';
import { useState } from 'react';
import { IMenuitems } from './MenuItems';
import { GridExpandMoreIcon } from '@mui/x-data-grid';

interface NavMenuItemType {
  item: IMenuitems;
  pathTo: string;
}
const NavMenuItem = ({ item, pathTo }: NavMenuItemType) => {
  const [open, setOpen] = useState(false);
  const { icon: Icon } = item;
  const itemIcon = Icon ? <Icon /> : null;
  return (
    <List component="ul" disablePadding key={item?.id && item.title}>
      <ListItemButton
        component={item?.href ? Link : 'div'}
        href={item?.href}
        disabled={item?.disabled}
        selected={pathTo === item?.href}
        onClick={() => setOpen(!open)}
      >
        <ListItemIcon
          sx={{
            py: 0.4,
            px: 0,
            ...(!item.available && {
              color: 'action.active',
              opacity: 0.9,
            }),
          }}
        >
          {itemIcon}
        </ListItemIcon>
        <ListItemText
          sx={{
            ...(!item.available && {
              color: 'action.active',
              opacity: 0.9,
            }),
          }}
        >
          {<>{`${item?.title}`}</>}
          <br />
          {item?.subtitle ? <Typography variant="caption">{item.subtitle}</Typography> : ''}
        </ListItemText>
        {item.children && (
          <ListItemSecondaryAction>
            <IconButton
              onClick={(e) => {
                e.stopPropagation();
                setOpen(!open);
              }}
              aria-label="Expand"
            >
              <GridExpandMoreIcon className={open ? 'rotate-180' : ''} />
            </IconButton>
          </ListItemSecondaryAction>
        )}
        {!item?.chip ? null : (
          <Chip
            color={item?.chipColor}
            variant={item?.variant ? item?.variant : 'outlined'}
            size="small"
            label={item?.chip}
            sx={({ palette, shape, typography }) => ({
              borderRadius: shape.borderRadius * 3,
              ...typography.caption,
              ...(pathTo === item?.href
                ? { bgcolor: palette.text.disabled, color: palette.primary.main }
                : {
                    bgcolor: palette.text.primary,
                    color: palette.common.white,
                  }),
            })}
          />
        )}
      </ListItemButton>
      {item.children && (
        <Collapse in={open} timeout="auto" unmountOnExit>
          <Box ml={2}>
            <List component="div" disablePadding>
              {item.children.map((child) => (
                <NavMenuItem key={child.id} item={child} pathTo={pathTo} />
              ))}
            </List>
          </Box>
        </Collapse>
      )}
    </List>
  );
};

export default NavMenuItem;
