import type { Meta, StoryObj } from '@storybook/react';
import { ProfilePage } from '../pages/ProfilePage';

const meta: Meta<typeof ProfilePage> = {
  title: 'Core/Pages/Profile Page',
  component: ProfilePage,
  // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/react/writing-docs/autodocs
  tags: ['autodocs'],
  parameters: {
    // More on Story layout: https://storybook.js.org/docs/react/configure/story-layout
    layout: 'fullscreen',
  },
};

export default meta;
type Story = StoryObj<typeof ProfilePage>;

export const Standard: Story = {};