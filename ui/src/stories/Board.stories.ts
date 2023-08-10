import type { Meta, StoryObj } from '@storybook/react';
import { Board } from '../components/Board';

const meta: Meta<typeof Board> = {
  title: 'Core/Components/Board',
  component: Board,
  // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/react/writing-docs/autodocs
  tags: ['autodocs'],
  parameters: {
    // More on Story layout: https://storybook.js.org/docs/react/configure/story-layout
    layout: 'fullscreen',
  },
};

export default meta;
type Story = StoryObj<typeof Board>;

export const Standard: Story = {
  args: {
    name: "Test board"
  }
};