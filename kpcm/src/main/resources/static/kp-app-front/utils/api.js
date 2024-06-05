export const fetcher = async (url, param) => {
  try {
    const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/${url}`, {
      method: 'POST',
      cache: 'no-store',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(param),
    });

    if (!res.ok) {
      throw new Error('Network response was not ok ' + res.statusText);
    }

    const data = await res.json();
    return data;
  } catch (error) {
    console.error('Error: ', error);
    throw error;
  }
};