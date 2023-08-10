import type { Meta, StoryObj } from '@storybook/react';
import { Category } from '../components/Category';

const meta: Meta<typeof Category> = {
  title: 'Core/Components/Category',
  component: Category,
  // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/react/writing-docs/autodocs
  tags: ['autodocs'],
  parameters: {
    // More on Story layout: https://storybook.js.org/docs/react/configure/story-layout
    layout: 'fullscreen',
  },
};

export default meta;
type Story = StoryObj<typeof Category>;

export const Default: Story = {};