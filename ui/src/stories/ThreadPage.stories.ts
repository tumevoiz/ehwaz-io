import type { Meta, StoryObj } from '@storybook/react';
import { ThreadPage } from '../pages/ThreadPage';

const meta: Meta<typeof ThreadPage> = {
  title: 'Core/Pages/Thread Page',
  component: ThreadPage,
  // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/react/writing-docs/autodocs
  tags: ['autodocs'],
  parameters: {
    // More on Story layout: https://storybook.js.org/docs/react/configure/story-layout
    layout: 'fullscreen',
  },
};

export default meta;
type Story = StoryObj<typeof ThreadPage>;

export const Standard: Story = {};