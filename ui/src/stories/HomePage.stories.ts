import type { Meta, StoryObj } from '@storybook/react';
import { HomePage } from '../pages/HomePage';

const meta: Meta<typeof HomePage> = {
  title: 'Core/Pages/Home Page',
  component: HomePage,
  // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/react/writing-docs/autodocs
  tags: ['autodocs'],
  parameters: {
    // More on Story layout: https://storybook.js.org/docs/react/configure/story-layout
    layout: 'fullscreen',
  },
};

export default meta;
type Story = StoryObj<typeof HomePage>;

export const Standard: Story = {};