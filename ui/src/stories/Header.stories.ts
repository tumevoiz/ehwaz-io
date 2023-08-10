import type { Meta, StoryObj } from '@storybook/react';
import { Header } from '../components/Header';

const meta: Meta<typeof Header> = {
  title: 'Core/Components/Header',
  component: Header,
  // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/react/writing-docs/autodocs
  tags: ['autodocs'],
  parameters: {
    // More on Story layout: https://storybook.js.org/docs/react/configure/story-layout
    layout: 'fullscreen',
  },
};

export default meta;
type Story = StoryObj<typeof Header>;

export const Standard: Story = {};