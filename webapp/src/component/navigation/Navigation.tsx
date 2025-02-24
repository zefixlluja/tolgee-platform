import React, { ReactNode } from 'react';
import { Breadcrumbs, Link } from '@mui/material';
import { NavigateNext } from '@mui/icons-material';
import { Link as RouterLink } from 'react-router-dom';

type Props = {
  path: [name: string | ReactNode, url: string][];
};

export const Navigation: React.FC<Props> = ({ path }) => {
  return (
    <Breadcrumbs
      aria-label="breadcrumb"
      separator={<NavigateNext fontSize="small" />}
    >
      {path.map(([name, url], index) => {
        return (
          <Link
            key={index}
            color={index === path.length - 1 ? 'primary' : 'inherit'}
            to={url}
            component={RouterLink}
          >
            {name}
          </Link>
        );
      })}
    </Breadcrumbs>
  );
};
