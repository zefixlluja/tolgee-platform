import API from '@openreplay/tracker';
import { PaletteColor } from '@mui/material/styles';
import { PaletteColorOptions } from '@mui/material';
import { Editor, Emphasis } from 'colors';

declare module '*.svg' {
  const content: React.FunctionComponent<React.SVGAttributes<SVGElement>>;
  export default content;
}

declare module '@mui/material/styles/createPalette' {
  interface Palette {
    divider2: PaletteColor;
    divider1: PaletteColor;
    cellSelected1: PaletteColor;
    cellSelected2: PaletteColor;
    default: PaletteColor;
    navbarBackground: PaletteColor;
    emphasis: Emphasis;
    editor: Editor;
  }

  interface PaletteOptions {
    divider2: PaletteColorOptions;
    divider1: PaletteColorOptions;
    cellSelected1: PaletteColorOptions;
    cellSelected2: PaletteColorOptions;
    default: PaletteColor;
    navbarBackground: PaletteColor;
    emphasis: Emphasis;
    editor: Editor;
  }
}

declare module '@mui/material/Button' {
  interface ButtonPropsColorOverrides {
    default: true;
  }
}

declare global {
  interface Window {
    openReplayTracker?: API;
  }
}
