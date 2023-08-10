import React from 'react';
import {UserIcon} from '@heroicons/react/24/outline';
export const Avatar: React.FC = () => {
  return (
    <div className="w-24 h-24 overflow-hidden rounded-full mt-2">
      <UserIcon />
    </div>
  )
}