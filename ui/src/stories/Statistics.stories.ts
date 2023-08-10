import type { Meta, StoryObj } from '@storybook/react';
import { Statistics } from '../components/Statistics';

const meta: Meta<typeof Statistics> = {
  title: 'Core/Components/Statistics',
  component: Statistics,
  // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/react/writing-docs/autodocs
  tags: ['autodocs'],
  parameters: {
    // More on Story layout: https://storybook.js.org/docs/react/configure/story-layout
    layout: 'fullscreen',
  },
};

export default meta;
type Story = StoryObj<typeof Statistics>;

export const Standard: Story = {};