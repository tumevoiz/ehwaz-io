import React from 'react';
import {HeartIcon} from '@heroicons/react/24/outline'
export const Footer: React.FC = () => {
  return (
    <footer className="flex justify-center flex-row gap-x-1 text-sm">
      <a href="https://ehwaz.io">Powered by ehwaz.io</a>
      <HeartIcon className="h-4 w-4" />
    </footer>
  )
}