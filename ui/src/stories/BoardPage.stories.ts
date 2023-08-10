import type { Meta, StoryObj } from '@storybook/react';
import { BoardPage } from '../pages/BoardPage';

const meta: Meta<typeof BoardPage> = {
  title: 'Core/Pages/Board Page',
  component: BoardPage,
  // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/react/writing-docs/autodocs
  tags: ['autodocs'],
  parameters: {
    // More on Story layout: https://storybook.js.org/docs/react/configure/story-layout
    layout: 'fullscreen',
  },
};

export default meta;
type Story = StoryObj<typeof BoardPage>;

export const Standard: Story = {};