import type { Meta, StoryObj } from '@storybook/react';
import { Post } from '../components/Post';

const meta: Meta<typeof Post> = {
  title: 'Core/Components/Post',
  component: Post,
  // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/react/writing-docs/autodocs
  tags: ['autodocs'],
  parameters: {
    // More on Story layout: https://storybook.js.org/docs/react/configure/story-layout
    layout: 'fullscreen',
  },
};

export default meta;
type Story = StoryObj<typeof Post>;

export const Standard: Story = {};